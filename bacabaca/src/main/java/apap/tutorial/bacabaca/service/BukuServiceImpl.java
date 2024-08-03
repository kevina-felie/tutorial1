package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.model.Buku;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BukuServiceImpl implements BukuService {
    private List<Buku> listBuku;

    public BukuServiceImpl() {
        listBuku = new ArrayList<Buku>();
    }

    @Override
    public void createBuku(Buku buku) {
        listBuku.add(buku);
    }

    @Override
    public List<Buku> getAllBuku() {
        return listBuku;
    }

    @Override
    public Buku getBukuById(UUID id) {
        Buku bukuYangDicari = null;
        for (Buku buku : listBuku) {
            if (buku.getId().equals(id)) {
                bukuYangDicari = buku;
            }
        }
        return bukuYangDicari;
    }

    @Override
    public Buku updateBuku(UUID id, Buku newBuku) {
        Buku bukuYangDiubah = getBukuById(id);

        if (bukuYangDiubah != null) {
            bukuYangDiubah.setJudul(newBuku.getJudul());
            bukuYangDiubah.setPenulis(newBuku.getPenulis());
            bukuYangDiubah.setTahunTerbit(newBuku.getTahunTerbit());
            bukuYangDiubah.setHarga(newBuku.getHarga());
        }

        return bukuYangDiubah;
    }

    @Override
    public boolean isJudulExist(String judul) {
        return listBuku.stream().anyMatch(b -> b.getJudul().equals(judul));
    }

    @Override
    public boolean isJudulExist(UUID id, String judul) {
        return listBuku.stream().anyMatch(b -> b.getJudul().equals(judul) && !b.getId().equals(id));
    }

    @Override
    public void deleteBuku(UUID id) {
        listBuku.remove(getBukuById(id));
    }
}
