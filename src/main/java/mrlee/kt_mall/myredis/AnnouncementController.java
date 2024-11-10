package mrlee.kt_mall.myredis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/announcement")
@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/")
    public ResponseEntity<List<Announcement>> announcementList() {
        List<Announcement> list = announcementService.findAnnouncements();
        return new ResponseEntity<>(list, OK);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Void> announcementModify(@PathVariable("id") String id, @RequestBody UpdateAnnouncementForm form) {
        announcementService.modifyAnnouncement(id, form);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> announcementDelete(@PathVariable("id") String id) {
        announcementService.deleteAnnouncement(id);
        return new ResponseEntity<>(OK);
    }
}
