package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.controller.DTO.BukuDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class BukuController {

    @Autowired
    private BukuService bukuService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model) {
        var bukuDTO = new BukuDTO();

        model.addAttribute("bukuDTO", bukuDTO);

        return "form-create-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@ModelAttribute BukuDTO bukuDTO, Model model) {
        UUID newId = UUID.randomUUID();

        var buku = new Buku(newId, bukuDTO.getJudul(), bukuDTO.getPenulis(), bukuDTO.getTahunTerbit(), bukuDTO.getHarga());

        bukuService.createBuku(buku);

        model.addAttribute("id", buku.getId());

        model.addAttribute("judul", buku.getJudul());

        return "success-create-buku";
    }

    @GetMapping("buku/viewall")
    public String listBuku(Model model) {
        List<Buku> listBuku = bukuService.getAllBuku();

        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    @GetMapping("buku")
    public String detailBuku(@RequestParam("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        model.addAttribute("buku", buku);
        return "view-buku";
    }
}
