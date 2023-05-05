package shop.mtcoding.village.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.interfaceTest.AbstractIntegrated;
import shop.mtcoding.village.util.status.ReservationStatus;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ReservationControllerTest")
public class ReservationControllerTest extends AbstractIntegrated {

    @Test
    @DisplayName("예약내역 전체보기")

    void getPage() throws Exception {

        this.mockMvc.perform(
                        get ("/reservation")
                                .header("Authorization", getUser())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("place-list",
                                responseFields(
                                ).and(getReservationResponseField("data[]."))
                        )

                );
    }

    @Test
    @DisplayName("예약내역 상세보기")
    void getDetailPage() throws Exception {

        this.mockMvc.perform(
                        get ("/reservation/1")
                                .header("Authorization", getUser())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("place-detail-list",
                                responseFields(
                                ).and(getReservationDetailResponseField("data."))
                        )

                );
    }

    @Test
    @DisplayName("예약 신청하기")
    void postReservation () throws Exception {

        ReservationSaveRequest request = new ReservationSaveRequest("예약자 이름", 3, "2023-03-03T15:12:22", "2023-03-03T15:12:22", "2023-03-03T15:12:22", ReservationStatus.WAIT);

        this.mockMvc.perform(
                        post("/reservation")
                                .header("Authorization", getUser())
                                .content(objectMapper.writeValueAsString(request))
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("reservation-save",
                                requestFields(getReservationRequestField()),
                                responseFields(getReservationResponseField("data."))
                        )
                );
    }



    private FieldDescriptor[] getReservationRequestField() {
        return new FieldDescriptor[] {
                fieldWithPath("userName").description("예약자 이름"),
                fieldWithPath("peopleNum").description("예약 인원수"),
                fieldWithPath("date").description("예약 날짜"),
                fieldWithPath("startTime").description("시작날짜"),
                fieldWithPath("endTime").description("마감날짜"),
                fieldWithPath("reservationStatus").description("예약상태")
        };
    }
    private FieldDescriptor[] getReservationResponseField(String prefix) {
        return new FieldDescriptor[] {
                fieldWithPath("code").description("응답 코드"),
                fieldWithPath("status").description("응답 상태"),
                fieldWithPath("msg").description("응답 메시지"),
                fieldWithPath(prefix+"id").description("공간의 id"),
                fieldWithPath(prefix+"user.id").description("유저 id"),
                fieldWithPath(prefix+"user.name").description("유저 이름"),
                fieldWithPath(prefix+"user.email").description("유저 이메일"),
                fieldWithPath(prefix+"user.tel").description("유저 전화번호"),
                fieldWithPath(prefix+"user.profile").description("유저 프로필"),
                fieldWithPath(prefix+"user.status").description("유저 활성화 상태"),
                fieldWithPath(prefix+"user.createdAt").description("유저 가입시간"),
                fieldWithPath(prefix+"place.id").description("공간 id"),
                fieldWithPath(prefix+"place.tel").description("공간 전화번호"),
                fieldWithPath(prefix+"place.title").description("공간 제목"),
                fieldWithPath(prefix+"place.placeIntroductionInfo").description("공간 정보"),
                fieldWithPath(prefix+"place.notice").description("공간 알림"),
                fieldWithPath(prefix+"place.fileInfo").description("공간 사진"),
                fieldWithPath(prefix+"place.startTime").description("시작시간"),
                fieldWithPath(prefix+"place.endTime").description("마감 시간"),
                fieldWithPath(prefix+"place.status").description("공간 상태"),
                fieldWithPath(prefix+"place.isConfirmed").description("공간 주소"),
                fieldWithPath(prefix+"place.maxPeople").description("공간 최대인원수"),
                fieldWithPath(prefix+"place.maxParking").description("주차가능대수"),
                fieldWithPath(prefix+"place.pricePerHour").description("시간당 결제금액"),
                fieldWithPath(prefix+"date").description("예약 날짜"),
                fieldWithPath(prefix+"startTime").description("예약 시작시간"),
                fieldWithPath(prefix+"endTime").description("예약 마감시간"),
                fieldWithPath(prefix+"peopleNum").description("예약 인원"),
                fieldWithPath(prefix+"status").description("예약 상태"),
        };
    }

    private FieldDescriptor[] getReservationDetailResponseField(String prefix) {
        return new FieldDescriptor[] {
                fieldWithPath("code").description("응답 코드"),
                fieldWithPath("status").description("응답 상태"),
                fieldWithPath("msg").description("응답 메시지"),
                fieldWithPath(prefix+"user.id").description("유저 id"),
                fieldWithPath(prefix+"user.name").description("유저 이름"),
                fieldWithPath(prefix+"user.email").description("유저 이메일"),
                fieldWithPath(prefix+"user.tel").description("유저 전화번호"),
                fieldWithPath(prefix+"user.profile").description("유저 프로필"),
                fieldWithPath(prefix+"user.status").description("유저 활성화 상태"),
                fieldWithPath(prefix+"user.createdAt").description("유저 가입시간"),
                fieldWithPath(prefix+"place.id").description("공간 id"),
                fieldWithPath(prefix+"place.tel").description("공간 전화번호"),
                fieldWithPath(prefix+"place.title").description("공간 제목"),
                fieldWithPath(prefix+"place.placeIntroductionInfo").description("공간 정보"),
                fieldWithPath(prefix+"place.notice").description("공간 알림"),
                fieldWithPath(prefix+"place.fileInfo").description("공간 사진"),
                fieldWithPath(prefix+"place.startTime").description("시작시간"),
                fieldWithPath(prefix+"place.endTime").description("마감 시간"),
                fieldWithPath(prefix+"place.status").description("공간 상태"),
                fieldWithPath(prefix+"place.isConfirmed").description("공간 주소"),
                fieldWithPath(prefix+"place.maxPeople").description("공간 최대인원수"),
                fieldWithPath(prefix+"place.maxParking").description("주차가능대수"),
                fieldWithPath(prefix+"place.pricePerHour").description("시간당 결제금액"),
                fieldWithPath(prefix+"date").description("예약 날짜"),
                fieldWithPath(prefix+"startTime").description("예약 시작시간"),
                fieldWithPath(prefix+"endTime").description("예약 마감시간"),
                fieldWithPath(prefix+"peopleNum").description("예약 인원"),
                fieldWithPath(prefix+"totalPrice").description("예약 총금액")
        };
    }


}