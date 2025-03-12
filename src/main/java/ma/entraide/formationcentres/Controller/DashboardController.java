package ma.entraide.formationcentres.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.entraide.formationcentres.Repository.ActiviteRepo;
import ma.entraide.formationcentres.Repository.BeneficiaireRepo;
import ma.entraide.formationcentres.Repository.CentreRepo;
import ma.entraide.formationcentres.Repository.PersonnelRepo;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	@Autowired
    private CentreRepo centreRepo;
    
    @Autowired
    private BeneficiaireRepo beneficiaireRepo;
    
    @Autowired
    private ActiviteRepo activiteRepo;
    
    @Autowired
    private PersonnelRepo personnelRepo;

    @GetMapping("/stats")
    public Map<String, Long> getStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("centres", centreRepo.count());
        stats.put("beneficiaires", beneficiaireRepo.count());
        stats.put("activites", activiteRepo.count());
        stats.put("personnels", personnelRepo.count());
        return stats;
    }

    @GetMapping("/activites-trend")
    public List<Map<String, Object>> getActivitesTrend() {
        List<Map<String, Object>> trend = new ArrayList<>();
        List<Object[]> result = activiteRepo.countActivitesPerMonth();
        
        for (Object[] row : result) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("month", row[0]);
            entry.put("count", row[1]);
            trend.add(entry);
        }
        return trend;
    }
}
