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
        if (bukuService.isJudulExist(bukuDTO.getJudul())){
            var errorMessage = "Judul sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

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

    @GetMapping(value="buku/{id}")
    public String detailBukuPath(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        model.addAttribute("buku", buku);
        return "view-buku";
    }

    @GetMapping("/buku/{id}/update")
    public String formUpdateBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        var bukuDTO = new BukuDTO(buku.getId(), buku.getJudul(), buku.getPenulis(), buku.getTahunTerbit(), buku.getHarga());

        model.addAttribute("bukuDTO", bukuDTO);

        return "form-update-buku";
    }

    @PostMapping("/buku/update")
    public String updateBuku(@ModelAttribute BukuDTO bukuDTO, Model model) {
        if (bukuService.isJudulExist(bukuDTO.getId(), bukuDTO.getJudul())){
            var errorMessage = "Judul sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        var buku = new Buku(bukuDTO.getId(), bukuDTO.getJudul(), bukuDTO.getPenulis(), bukuDTO.getTahunTerbit(), bukuDTO.getHarga());

        bukuService.updateBuku(buku.getId(), buku);

        model.addAttribute("id", buku.getId());

        model.addAttribute("judul", buku.getJudul());

        return "success-update-buku";
    }

    @GetMapping("/buku/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id, Model model) {
        bukuService.deleteBuku(id);

        model.addAttribute("id", id);

        return "success-delete-buku";
    }
}
