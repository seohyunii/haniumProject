package com.example.haniumproject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/MedSettings")
    Call<MedpopResponse> userMed(@Body MedpopData data);

    @POST("/user/getmedtakentime") //복용한 약 시각 가져오기
    Call<MedInfoResponse> userGetMedTime(@Body MedInfoData data);

    // 약 복용 정보 출력 (1~6칸)
    @POST("/user/medlist")
    Call<MedListResponse> userMedList(@Body MedListData data);

    // 약 복용 정보 출력 (pop - 1칸)
    @POST("/user/medpoplist")
    Call<MedpopListResponse> userMedpopList(@Body MedpopListData data);

    // 약 복용 정보 출력 (pop - 1칸)
    @POST("/user/meddel")
    Call<MeddelResponse> userMeddel(@Body MeddelData data);

    // 약 복용 정보 출력 (pop - 1칸)
    @POST("/user/medpopwarning")
    Call<MedpopwarningResponse> userMedpopwarning(@Body MedpopwarningData data);
}
