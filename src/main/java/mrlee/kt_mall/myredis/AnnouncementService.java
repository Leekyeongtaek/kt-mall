package mrlee.kt_mall.myredis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AnnouncementService {

    private final AnnounceRepository announceRepository;

    public List<Announcement> findAnnouncements() {
        List<Announcement> list = new ArrayList<>();
        announceRepository.findAll().forEach(list::add);
        return list;
    }

    public void modifyAnnouncement(String id, UpdateAnnouncementForm form) {
        Announcement announcement = announceRepository.findById(id).get();
        Announcement update = announcement.update(form.getTitle(), form.getContent());
        announceRepository.save(update);
    }

    public void deleteAnnouncement(String id) {
        announceRepository.deleteById(id);
    }
}
