package redwine.me.jpabbs.bbs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/bbs/v1/api")
@RestController
public class BbsController {

    private final BbsServices bbsServices;


    @GetMapping("/read/{id}")
    public ResponseEntity<BbsResponse> getBbs(@PathVariable long id) {
        BbsResponse bbs = bbsServices.getBbs(id);
        return ResponseEntity.ok(bbs);
    }

    /**
     * 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        bbsServices.deleteBbs(id);
        return ResponseEntity.created(URI.create("/bbs/v1/api/list")).build();
    }

    /**
     * 목록 조회
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<BbsResponse>> getListAll() {
        List<BbsResponse> toList = bbsServices.findBbsToList();
        return ResponseEntity.ok(toList);
    }

    /**
     * 수젇
     * @param id
     * @param bbs
     * @return
     */
    @PutMapping("/modify/{id}/edit")
    public ResponseEntity<Void> modify(@PathVariable Long id, @RequestBody BbsRequest bbs) {
        bbsServices.modifyBbs(id, bbs);

        return ResponseEntity.ok().build();
    }

    /**
     * 등록
     * @param bbsRequest
     * @return
     */
    @PostMapping("/post")
    public ResponseEntity<Void> post(@RequestBody BbsRequest bbsRequest) {
        Long saveId = bbsServices.createBbs(bbsRequest);
        return ResponseEntity.created(URI.create("/bbs/v1/api/list")).build();
    }
}
