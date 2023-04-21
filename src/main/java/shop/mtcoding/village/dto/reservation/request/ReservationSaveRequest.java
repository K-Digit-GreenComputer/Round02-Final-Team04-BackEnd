package shop.mtcoding.village.dto.reservation.request;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@ToString
public class ReservationSaveRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    private Integer peopleNum;

//    @NotBlank(message = "날짜를 선택해주세요.")
    private LocalDateTime date;

//    @NotBlank(message = "시작 시간을 입력해주세요.")
    private LocalDateTime startTime;

//    @NotBlank(message = "끝 시간을 입력해주세요.")
    private LocalDateTime endTime;

    public Reservation toEntity() {
        User user = new User();
        user.setName(userName);

        Reservation reservation = new Reservation();
        reservation.setPeopleNum(peopleNum);
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        return new Reservation(user, date, startTime, endTime, peopleNum);
    }

}