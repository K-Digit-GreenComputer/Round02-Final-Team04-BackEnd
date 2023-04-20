package shop.mtcoding.village.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.hashtag.HashtagRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hashtag")
public class TestController {

    private final HashtagRepository hashtagRepository;

    private final PlaceRepository placeRepository;


    public TestController(HashtagRepository hashtagRepository, PlaceRepository placeRepository) {
        this.hashtagRepository = hashtagRepository;
        this.placeRepository = placeRepository;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody HashtagSaveDTO hashtagDTO) {

        Optional<Place> byId = placeRepository.findById(2L);
        Place place1 = byId.get();
        List<Hashtag> hashtagList = new ArrayList<>();


        for (HashtagSaveDTO.HashtagDto hash : hashtagDTO.getHashtagDto()) {
            Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place1));

            hashtagList.add(save1);
        }

        System.out.println("디버그 : " +  hashtagDTO);

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "해시태그 넣기", hashtagList), HttpStatus.OK);
    }
}