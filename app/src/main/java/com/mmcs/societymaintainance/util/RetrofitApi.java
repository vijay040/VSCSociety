package com.mmcs.societymaintainance.util;

import com.mmcs.societymaintainance.model.DesignationRestMeta;
import com.mmcs.societymaintainance.model.EmployeeRestMeta;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.model.LoginResMeta;
import com.mmcs.societymaintainance.model.ResponseMeta;
import com.mmcs.societymaintainance.model.UnitRestMeta;
import com.mmcs.societymaintainance.model.VisitorRestMeta;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApi {


    @FormUrlEncoded
    @POST("apilogin.php")
    Call<LoginResMeta> login(@Field("username") String email, @Field("password") String password, @Field("device_token") String device_token, @Field("fcm_token") String fcm_token, @Field("ddlBranch") String ddlBranch, @Field("ddlLoginType") String ddlLoginType);

    @FormUrlEncoded
    @POST("visitor_get_api.php")
    Call<VisitorRestMeta> getVisitorList(@Field("user_id") String user_id, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("employee_get_api.php")
    Call<EmployeeRestMeta> getEmployeeList(@Field("user_id") String user_id, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("floorlist_get_api.php")
    Call<ResponseMeta> getFloorList( @Field("user_id") String user_id,@Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("unitlist_get_api.php")
    Call<UnitRestMeta> getUnitList(@Field("user_id") String user_id, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("get_designation_api.php")
    Call<DesignationRestMeta> getDesignationList(@Field("user_id") String user_id, @Field("branch_id") String branch_id);


    @Multipart
    @POST("add_visitor_post_api.php")
    Call<LoginResMeta> postVisitor(@Part("user_id") RequestBody user_id, @Part("branch_id") RequestBody branch_id,
                                     @Part("txtName") RequestBody txtName,@Part("txtIssueDate") RequestBody txtIssueDate ,@Part("txtMobile") RequestBody txtMobile, @Part("txtAddress") RequestBody txtAddress,
                                     @Part("ddlFloorNo") RequestBody ddlFloorNo , @Part("ddlUnitNo") RequestBody ddlUnitNo, @Part("txtInTime") RequestBody txtInTime,@Part("txtOutTime") RequestBody txtOutTime ,@Part("image\"; filename=\"profile.jpg") RequestBody image

    );

    @Multipart
    @POST("add_employee_post_api.php")
    Call<LoginResMeta> postEmployee(@Part("user_id") RequestBody user_id, @Part("branch_id") RequestBody branch_id,
                                   @Part("txtEmpName") RequestBody txtEmpName,@Part("txtEmpEmail") RequestBody txtEmpEmail ,@Part("txtEmpContact") RequestBody txtEmpContact, @Part("txtEmpPreAddress") RequestBody txtEmpPreAddress,
                                   @Part("txtEmpPerAddress") RequestBody txtEmpPerAddress , @Part("txtEmpNID") RequestBody txtEmpNID, @Part("ddlMemberType") RequestBody ddlMemberType,@Part("txtEmpDate") RequestBody txtEmpDate,@Part("txtEndingDate") RequestBody txtEndingDate ,@Part("e_password") RequestBody e_password,@Part("e_status") RequestBody e_status,@Part("added_date") RequestBody added_date,@Part("image\"; filename=\"profile.jpg") RequestBody image

    );



    @FormUrlEncoded
    @POST("update_visitor_api.php")
    Call<UnitRestMeta> updateVisitor(@Field("vid") String vid, @Field("txtOutTime") String txtOutTime);

    @FormUrlEncoded
    @POST("update_employee_api.php")
    Call<UnitRestMeta> updateEmployee(@Field("eid") String eid, @Field("ending_date") String ending_date);


}
