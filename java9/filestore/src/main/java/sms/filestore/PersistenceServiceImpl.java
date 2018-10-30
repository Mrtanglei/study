package sms.filestore;

import sms.model.Entity;
import sms.persistence.PersistenceService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tanglei
 * @date 18/10/29
 */
public class PersistenceServiceImpl implements PersistenceService {

    private final Path dataPath = Paths.get("/", "data");

    @Override
    public <T extends Entity> List<T> list(Class<T> type) {
        List<T> list = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getEntitiesPath(type), "*.bin")) {
            for (Path path : directoryStream) {
                list.add(readEntity(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T extends Entity> Optional<T> get(Class<T> type, String id) {
        try {
            return Optional.ofNullable(readEntity(getEntityPath(type, id)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Entity entity) {
        try {
            saveEntity(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends Entity> void delete(Class<T> type, String id) {
        try {
            deleteEntity(type, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getEntitiesPath(Class<?> type) {
        return dataPath.resolve(type.getSimpleName());
    }

    private Path getEntityPath(Class<?> type, String id) {
        //在实体文件夹下以.bin结尾的文件
        return getEntitiesPath(type).resolve(String.format("%s.bin", id));
    }

    private <T extends Entity> T readEntity(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
            return (T) inputStream.readObject();
        }
    }

    private void saveEntity(Entity entity) throws IOException {
        Path path = getEntityPath(entity.getClass(), entity.getId());
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(entity);
        }
    }

    private void deleteEntity(Class<?> type, String id) throws IOException {
        Files.deleteIfExists(getEntityPath(type, id));
    }
}
