package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.model.Buku;

import java.util.List;
import java.util.UUID;

public interface BukuService {
    void createBuku(Buku buku);

    List<Buku> getAllBuku();

    Buku getBukuById(UUID id);
}
