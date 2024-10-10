package redwine.me.jpabbs.bbs;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BbsServices {

    private final BbsRepository bbsRepository;

    //등록
    @Transactional
    public Long createBbs(BbsRequest bbsRequest) {
        Bbs bbs = bbsRequest.toEntity();
        Bbs saved = bbsRepository.save(bbs);
        return saved.getId();
    }

    //수정
    @Transactional
    public void modifyBbs(Long id, BbsRequest request) {
        Bbs findBbs = bbsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
        findBbs.modifyBbs(request);
    }

    //삭제
    @Transactional
    public void deleteBbs(Long id) {
        bbsRepository.deleteById(id);
    }

    //조회-단일
    public BbsResponse getBbs(Long id) {
        Bbs bbs = bbsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
        return BbsResponse.of(bbs);
    }

    //조회-목록
    public List<BbsResponse> findBbsToList() {
        List<Bbs> bbsList = bbsRepository.findAll();
        return Collections.unmodifiableList(BbsResponse.listOf(bbsList));
    }

}
