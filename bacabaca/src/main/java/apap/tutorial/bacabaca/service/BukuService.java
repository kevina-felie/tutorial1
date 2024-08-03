package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.model.Buku;

import java.util.List;
import java.util.UUID;

public interface BukuService {
    void createBuku(Buku buku);

    List<Buku> getAllBuku();

    Buku getBukuById(UUID id);

    Buku updateBuku(UUID id, Buku buku);

    boolean isJudulExist(String judul);

    boolean isJudulExist(UUID id, String judul);

    void deleteBuku(UUID id);

}
