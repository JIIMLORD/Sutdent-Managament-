package com.example.studentmanagement.model;

public class TaiKhoan {
    private int mId;
    private String mTenTaiKhoan;
    private String mMatkhau;
    private String mEmail;
    private int mPhanQuyen;

    public TaiKhoan(String mTenTaiKhoan, String mMatkhau, String mEmail, int mPhanQuyen) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatkhau = mMatkhau;
        this.mEmail = mEmail;
        this.mPhanQuyen = mPhanQuyen;
    }

    public TaiKhoan(String mTenTaiKhoan, String mEmail) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setmTenTaiKhoan(String mTenTaiKhoan) {
        this.mTenTaiKhoan = mTenTaiKhoan;
    }

    public String getmMatkhau() {
        return mMatkhau;
    }

    public void setmMatkhau(String mMatkhau) {
        this.mMatkhau = mMatkhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }
}
