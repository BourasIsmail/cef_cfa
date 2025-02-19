package ma.entraide.formationcentres.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.entraide.formationcentres.Entity.BenefRequest;
import ma.entraide.formationcentres.Entity.BenefRequestDTO;
import ma.entraide.formationcentres.Service.BenefRequestService;

@RestController
@CrossOrigin("*")
@RequestMapping("/benefRequest")
public class BenefRequestController {
	@Autowired
    private BenefRequestService benefRequestService;

    @PostMapping("/add")
    public ResponseEntity<BenefRequest> createBenefRequest(@RequestBody BenefRequestDTO requestDTO) {
        BenefRequest benefRequest = benefRequestService.createBenefRequest(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(benefRequest);
    }

    @GetMapping
    public ResponseEntity<List<BenefRequest>> getAllBenefRequests() {
        return ResponseEntity.ok(benefRequestService.getAllBenefRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenefRequest> getBenefRequestById(@PathVariable Long id) {
        return benefRequestService.getBenefRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenefRequest(@PathVariable Long id) {
        benefRequestService.deleteBenefRequest(id);
        return ResponseEntity.noContent().build();
    }
}
