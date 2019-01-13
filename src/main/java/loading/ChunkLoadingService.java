package loading;

public interface ChunkLoadingService {
    long executeChunkLoading(String fileSource, int chunkSize);
}
