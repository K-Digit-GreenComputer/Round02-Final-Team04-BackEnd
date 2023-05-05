package shop.mtcoding.village.controller;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.file.dto.FileSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.interfaceTest.AbstractIntegrated;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class PlaceControllerTest extends AbstractIntegrated {

    @Test
    void getPage() throws Exception {

        this.mockMvc.perform(
                        get ("/places")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("place-list",
                                responseFields(
                                ).and(getPlaceResponseField("data[]."))
                        )

                );
    }

    @Test
    void saveSuccess() throws Exception {

        DateSaveDTO.DateSaveDto dates = new DateSaveDTO.DateSaveDto();
        dates.setDayOfWeekName("월요일");
        dates.setDayOfWeekName("화요일");
        dates.setDayOfWeekName("수요일");

        HashtagSaveDTO.HashtagSaveDto hashtag = new HashtagSaveDTO.HashtagSaveDto();
        hashtag.setHashtagName("해시태그1");
        hashtag.setHashtagName("해시태그2");
        hashtag.setHashtagName("해시태그3");

        FacilityInfoSaveDTO.FacilityInfoSaveDto facility = new FacilityInfoSaveDTO.FacilityInfoSaveDto();
        facility.setFacilityName("편의시설1");
        facility.setFacilityName("편의시설2");
        facility.setFacilityName("편의시설3");

        FileSaveDTO.FileSaveDto file = new FileSaveDTO.FileSaveDto();
        file.setFileName("파일이름1");
        file.setFileUrl("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");
        file.setFileName("파일이름2");
        file.setFileUrl("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");
        file.setFileName("파일이름3");
        file.setFileUrl("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");

        AddressDTO address = new AddressDTO("도로명주소", "시군구", "우편번호", "상세주소", "경도", "위도");

        PlaceSaveRequest request = new PlaceSaveRequest("제목", address, "전화번호", "2023-03-03T15:12:22", "2023-03-03T15:12:22", "공간정보"
                , "유의사항", 5, 3, 3000
                , List.of(dates), List.of(hashtag), List.of(facility), "카테고리", List.of(file));
        this.mockMvc.perform(
                        post("/places/host")
                                .header("Authorization", getHost())
                                .content(objectMapper.writeValueAsString(request))
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("place-save",
                                requestFields(getPLaceRequestField()),
                                responseFields(getPlaceResponseField("data."))
                        )
                );

    }

    private FieldDescriptor[] getPLaceRequestField() {
        return new FieldDescriptor[] {
                fieldWithPath("title").description("제목"),
                fieldWithPath("address.address").description("도로명주소"),
                fieldWithPath("address.sigungu").description("시군구"),
                fieldWithPath("address.zonecode").description("우편번호"),
                fieldWithPath("address.detailAddress").description("상세주소"),
                fieldWithPath("address.x").description("경도"),
                fieldWithPath("address.y").description("위도"),
                fieldWithPath("tel").description("전화번호"),
                fieldWithPath("startTime").description("12:10"),
                fieldWithPath("endTime").description("14:00"),
                fieldWithPath("placeIntroductionInfo").description("공간정보"),
                fieldWithPath("notice").description("유의사항"),
                fieldWithPath("maxPeople").description(5),
                fieldWithPath("maxParking").description(3),
                fieldWithPath("pricePerHour").description(3000),
                fieldWithPath("dayOfWeek[].dayOfWeekName").description("월요일"),
                fieldWithPath("dayOfWeek[].placeId").description("공간Id"),
                fieldWithPath("dayOfWeek[].dayOfWeekName").description("화요일"),
                fieldWithPath("dayOfWeek[].placeId").description("공간Id"),
                fieldWithPath("dayOfWeek[].dayOfWeekName").description("수요일"),
                fieldWithPath("dayOfWeek[].placeId").description("공간Id"),
                fieldWithPath("hashtag[].hashtagName").description("해시태그1"),
                fieldWithPath("hashtag[].placeId").description("공간Id"),
                fieldWithPath("hashtag[].hashtagName").description("해시태그2"),
                fieldWithPath("hashtag[].placeId").description("공간Id"),
                fieldWithPath("hashtag[].hashtagName").description("해시태그3"),
                fieldWithPath("hashtag[].placeId").description("공간Id"),
                fieldWithPath("facilityInfo[].facilityName").description("편의시설1"),
                fieldWithPath("facilityInfo[].placeId").description("공간Id"),
                fieldWithPath("facilityInfo[].facilityName").description("편의시설2"),
                fieldWithPath("facilityInfo[].placeId").description("공간Id"),
                fieldWithPath("facilityInfo[].facilityName").description("편의시설3"),
                fieldWithPath("facilityInfo[].placeId").description("공간Id"),
                fieldWithPath("categoryName").description("카테고리"),
                fieldWithPath("image[].fileName").description("파일이름1"),
                fieldWithPath("image[].fileUrl").description("파일URL1"),
                fieldWithPath("image[].extension").description("파일확장자1"),
                fieldWithPath("image[].status").description("파일상태1"),
                fieldWithPath("image[].type").description("파일타입1"),
                fieldWithPath("image[].fileName").description("파일이름2"),
                fieldWithPath("image[].fileUrl").description("파일URL2"),
                fieldWithPath("image[].extension").description("파일확장자2"),
                fieldWithPath("image[].status").description("파일상태2"),
                fieldWithPath("image[].type").description("파일타입2"),
                fieldWithPath("image[].fileName").description("파일이름3"),
                fieldWithPath("image[].fileUrl").description("파일URL3"),
                fieldWithPath("image[].extension").description("파일확장자3"),
                fieldWithPath("image[].status").description("파일상태3"),
                fieldWithPath("image[].type").description("파일타입3"),
        };
    }

    private FieldDescriptor[] getPlaceResponseField(String prefix) {
        return new FieldDescriptor[] {
                fieldWithPath("code").description("응답 코드"),
                fieldWithPath("status").description("응답 상태"),
                fieldWithPath("msg").description("응답 메시지"),
                fieldWithPath(prefix+"id").description("공간의 id"),
                fieldWithPath(prefix+"title").description("공간 제목"),
                fieldWithPath(prefix+"tel").description("공간 전화번호"),
                fieldWithPath(prefix+"placeIntroductionInfo").description("공간 정보"),
                fieldWithPath(prefix+"notice").description("공간 유의사항"),
                fieldWithPath(prefix+"fileInfo").description("공간 파일"),
                fieldWithPath(prefix+"startTime").description("공간 시작시간"),
                fieldWithPath(prefix+"endTime").description("공간 마감시간"),
                fieldWithPath(prefix+"status").description("공간 상태"),
                fieldWithPath(prefix+"isConfirmed").description("예약 가능 상태"),
                fieldWithPath(prefix+"maxPeople").description("공간 최대인원수"),
                fieldWithPath(prefix+"maxParking").description("주차가능대수"),
                fieldWithPath(prefix+"pricePerHour").description("시간당 결제금액")
        };
    }

}