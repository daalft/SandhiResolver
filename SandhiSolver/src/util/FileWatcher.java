package util;
import static java.nio.file.StandardWatchEventKinds.*;

import java.nio.file.*;

import de.general.log.ILogInterface;

public class FileWatcher {

	public void watch (Path path, ILogInterface log) throws Exception {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		for (;;) {
			// wait for key to be signaled
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}
			for (WatchEvent<?> event: key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW) {
					continue;
				}

				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
				Path filename = ev.context();

				if (filename.toString().equals("rules.txt")) {

					if (kind == ENTRY_CREATE) {
						log.debug("File creation: " + filename);
						Path child = path.resolve(filename);
						if (!Files.probeContentType(child).equals("text/plain")) {
							log.warn("Created file " + filename + " is not plain text!");
							continue;
						}
					}
					if (kind == ENTRY_MODIFY) {
						// TODO reload
						log.debug("Modification: " + filename);
					}
					if (kind == ENTRY_DELETE) {
						// TODO do something
						log.debug("Deletion: " + filename);
						continue;
					}
				} else {
					continue;
				}
			}
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}
