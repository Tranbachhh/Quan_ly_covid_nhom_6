USE demo;

-- 1.Admin
CREATE TABLE users(
                           id INT NOT NULL AUTO_INCREMENT,
                           Username VARCHAR(255) UNIQUE NOT NULL,
                           Password VARCHAR(255) NOT NULL,
                           CONSTRAINT PK_users PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE users
    ADD UNIQUE(Username);

INSERT INTO users(Username, Password) VALUES
    ('admin', 1);
-- 2. Nhân khẩu
CREATE TABLE nhan_khau(
                          idNhanKhau INT NOT NULL AUTO_INCREMENT,
                          hoTen NVARCHAR(255) NOT NULL,
                          ngaySinh DATE NOT NULL,
                          noiSinh NVARCHAR(255) NOT NULL,
                          gioiTinh NVARCHAR(255) NOT NULL,
                          nguyenQuan NVARCHAR(255) NOT NULL,
                          danToc NVARCHAR(255) NOT NULL,
                          tonGiao NVARCHAR(255) NOT NULL,
                          quocTich NVARCHAR(255) NOT NULL,
                          ngheNghiep NVARCHAR(255),
                          noiLamViec NVARCHAR(255),
                          cmnd VARCHAR(255),
                          ngayCap DATE,
                          noiCap NVARCHAR(255),
                          chuyenDenNgay DATE,
                          noiThuongTruTruoc NVARCHAR(255),
                          trangThai NVARCHAR(255) NOT NULL,
                          CONSTRAINT PK_nhan_khau PRIMARY KEY(idNhanKhau),
                          CONSTRAINT CHK_nhan_khau_gioi_tinh CHECK (gioiTinh IN (N'Nam', N'Nữ'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 3. Hộ khẩu
CREATE TABLE ho_khau(
                        idHoKhau INT NOT NULL AUTO_INCREMENT,
                        idChuHo INT NOT NULL,
                        tinhThanhPho NVARCHAR(255) NOT NULL,
                        quanHuyen NVARCHAR(255) NOT NULL,
                        phuongXa NVARCHAR(255) NOT NULL,
                        diaChi NVARCHAR(255) NOT NULL,
                        ngayTao DATE NOT NULL,
                        CONSTRAINT PK_ho_khau PRIMARY KEY(idHoKhau),
                        CONSTRAINT FK_ho_khau_nhan_khau FOREIGN KEY(idChuHo) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 4. Hộ khẩu - nhân khẩu
CREATE TABLE ho_khau_nhan_khau(
                                  idHoKhau INT NOT NULL ,
                                  idNhanKhau INT NOT NULL ,
                                  quanHeChuHo NVARCHAR(255) NOT NULL,
                                  CONSTRAINT PK_ho_khau_nhan_khau PRIMARY KEY (idHoKhau, idNhanKhau),
                                  CONSTRAINT FK_ho_khau_nhan_khau_ho_khau FOREIGN KEY(idHoKhau) REFERENCES ho_khau(idHoKhau) ON DELETE CASCADE,
                                  CONSTRAINT FK_ho_khau_nhan_khau_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 5. Tạm trú
CREATE TABLE tam_tru(
                        id INT NOT NULL AUTO_INCREMENT,
                        idNhanKhau INT NOT NULL,
                        noiThuongTru NVARCHAR(255) NOT NULL,
                        noiTamTru NVARCHAR(255) NOT NULL,
                        tuNgay DATE NOT NULL,
                        denNgay DATE NOT NULL,
                        lyDo NVARCHAR(255),
                        CONSTRAINT PK_tam_tru PRIMARY KEY(id),
                        CONSTRAINT FK_tam_tru_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 6. Tạm vắng
CREATE TABLE tam_vang(
                         id INT NOT NULL AUTO_INCREMENT,
                         idNhanKhau INT NOT NULL,
                         noiTamTru NVARCHAR(255) NOT NULL,
                         tuNgay DATE NOT NULL,
                         denNgay DATE NOT NULL,
                         lyDo NVARCHAR(255),
                         CONSTRAINT PK_tam_vang PRIMARY KEY(id),
                         CONSTRAINT FK_tam_vang_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 7. Khai tử
CREATE TABLE khai_tu(
                        idNguoiMat INT NOT NULL,
                        idNguoiKhai INT NOT NULL,
                        ngayKhai DATE NOT NULL,
                        ngayMat DATE NOT NULL,
                        liDoMat NVARCHAR(255),
                        CONSTRAINT  PK_khai_tu PRIMARY KEY(idNguoiMat),
                        CONSTRAINT FK_khai_tu_nguoi_mat_nhan_khau FOREIGN KEY(idNguoiMat) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE,
                        CONSTRAINT FK_khai_tu_nguoi_khai_nhan_khau FOREIGN KEY(idNguoiKhai) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 8. Chuyển nhân khẩu
CREATE TABLE chuyen_nhan_khau(
                                 id INT NOT NULL AUTO_INCREMENT,
                                 idNhanKhau INT NOT NULL,
                                 ngayChuyenDi DATE NOT NULL,
                                 noiChuyenDen NVARCHAR(255) NOT NULL,
                                 ghiChu NVARCHAR(255),
                                 CONSTRAINT PK_chuyen_nhan_khau PRIMARY KEY (id),
                                 CONSTRAINT FK_chuyen_nhan_khau_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 9 .Test
CREATE TABLE test_covid(
                                 id INT NOT NULL AUTO_INCREMENT,
                                 idNhanKhau INT NOT NULL,
                                 thoiDiemTest DATE NOT NULL,
                                 hinhThucTest NVARCHAR(255) NOT NULL,
                                 ketQua NVARCHAR(255) NOT NULL,
                                 CONSTRAINT PK_test_covid PRIMARY KEY (id),
                                 CONSTRAINT FK_test_covid_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 10 .Cách ly
CREATE TABLE cach_ly(
                                 id INT NOT NULL AUTO_INCREMENT,
                                 idNhanKhau INT NOT NULL,
                                 ngayBatDau DATE NOT NULL,
                                 soNgayCachLy INT NOT NULL,
                                 noiCachLy NVARCHAR(255) NOT NULL,
                                 mucDoCachLy NVARCHAR(50) NOT NULL,
                                 lyDoCachLy NVARCHAR(255),
                                 CONSTRAINT PK_cach_ly PRIMARY KEY (id),
                                 CONSTRAINT FK_cach_ly_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- 11 .Khai báo
CREATE TABLE khai_bao(
                                 id INT NOT NULL AUTO_INCREMENT,
                                 idNhanKhau INT NOT NULL,
                                 ngayKhaiBao DATE NOT NULL,
                                 trieuChung NVARCHAR(255) NOT NULL,
                                 tinhTrangSucKhoe NVARCHAR(255) NOT NULL,
                                 CONSTRAINT PK_khai_bao PRIMARY KEY (id),
                                 CONSTRAINT FK_khai_bao_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 12 .Tiêm Covid
CREATE TABLE tiem_covid(
                                 id INT NOT NULL AUTO_INCREMENT,
                                 idNhanKhau INT NOT NULL,
								 noiTiem NVARCHAR(255) NOT NULL,
                                 soMui INT NOT NULL,
                                 CONSTRAINT PK_tiem_covid PRIMARY KEY (id),
                                 CONSTRAINT FK_tiem_covid_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO nhan_khau(hoTen, ngaySinh, noiSinh, gioiTinh, nguyenQuan, danToc, tonGiao, quocTich, ngheNghiep, noiLamViec, cmnd, ngayCap,noiCap, chuyenDenNgay, noiThuongTruTruoc, trangThai) VALUES
                                                                                                                                                                                                 (N'Hoàng Đức Thành', '2002-01-26', N'Bệnh viện Đa Khoa Hưng Yên', N'Nam', N'Hưng Yên', N'Kinh', N'Không', N'Việt Nam', N'Sinh Viên', N'Đại học Bách khoa Hà Nội', 12345678439, '2018-8-8',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2022-12-11', N'Số 43 Đông Tác, Quận Đống Đa, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Lê Duy Anh', '2002-11-15', N'Liên Bang Nga', N'Nam', N'Hưng Yên', N'Kinh', N'Không', N'Việt Nam', N'Thiết kế đồ họa', N'Công ty Graphics Design', 123456781, '2019-4-11',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2021-12-12', N'Số 43 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Nguyễn Minh An', '2002-03-01', N'Bệnh viện Đa Khoa Hà Nam', N'Nam', N'Hà Nam', N'Kinh', N'Không', N'Việt Nam', N'Cơ Thủ', N'Liên Đoàn Bi a quốc gia ', 123456782, '2018-10-2',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2010-6-17', N'Số 57 Lương Khánh Thiện, Quận Hoàng Mai Hà Nội', N'Tạm Trú'),
                                                                                                                                                                                                 (N'Trần Xuân Bách', '2002-06-20', N'Bệnh viện Sản Nhi Trung Ương', N'Nam', N'Hưng Yên', N'Kinh', N'Không', N'Việt Nam', N'Chủ Tịch', N'Công ty TNHHMTV Bachchan', 123456783, '2016-10-3',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2020-10-29', N'Số 75 Giải Phóng, Quận Hai Bà Trưng, Hà Nội', N'Thường Trú'),
                                                                                                                                                                                                 (N'Phạm Anh Tú', '2002-10-19', N'Bệnh viện Hưng Hà', N'Nam', N'Hưng Yên', N'Kinh', N'Không', N'Việt Nam', N'Poker Star', N'Tại nhà', 123456784, '2018-10-23',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2019-12-15', N'Số 43 Yên Phụ, Quận Tây Hồ, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Trần Hoàng Hiệp', '1999-09-15', N'Bệnh viện Đại học Y ', N'Nam', N'Sài Gòn', N'Kinh', N'Thiên chúa giáo', N'Việt Nam', N'Thợ Kéo Cáp', N'VNPT', 123456785, '2016-10-5',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2019-12-11', N'Số 4 Phương Mai, Quận Đống Đa, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Lương Thị Phương', '1996-12-12', N'Bệnh viện Hà Nội', N'Nữ', N'Bắc Giang', N'Kinh', N'Phật giáo', N'Việt Nam', N'Quản Lí Khách Sạn', N'Khách sạn Anh Tú', 123456786, '2015-10-6',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2019-12-11', N'Số 25 Chùa Láng, Quận Đống Đa, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Nguyễn Sơn Tùng', '1994-06-12', N'Bệnh viện Đa Khoa Thái Bình', N'Nam', N'Thái Bình', N'Kinh', N'Không', N'Việt Nam', N'Ca Sĩ', N'Mtp Entertainment', 123456787, '2009-10-7',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2022-12-12', N'Số 45 Giảng Võ, Quận Đống Đa, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Yasuo Leesin', '1990-09-02', N'Bệnh viện Bạch Mai', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Đấu sĩ', N'Liên Minh Công Lí', 123456788, '2004-10-8',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2019-12-13', N'Số 5 Trương Định, Quận Hai Bà Trưng, Hà Nội', N'Tạm vắng'),
                                                                                                                                                                                                 (N'Lê Quang Liêm', '1987-05-25', N'Bệnh viện Chợ Rẫy', N'Nam', N'Sài Gòn', N'Kinh', N'Không', N'Việt Nam', N'Kì Thủ ', N'Liên Đoàn Cờ Vua Quốc Gia', '', NULL,NULL, '2019-12-14', N'Số 5 Cầu Giấy, Quận Cầu Giấy, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Nguyễn Thu Hà', '2019-01-15', N'Bệnh viện Việt Đức', N'Nữ', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Học sinh', N'Trường tiểu học Khương Trung', '', NULL,NULL, '2019-12-16', N'Số 4 Bạch Mai, Quận Hai Bà Trưng, Hà Nội', N'Tạm trú'),
                                                                                                                                                                                                 (N'Đặng Hải Triều', '1995-06-04', N'Bệnh viện Việt Tiệp', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Drop Test', N'Schanel', 123456780, '2013-10-9',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2020-12-15', N'Số 51 Nguyễn Trãi, Quận Thanh Xuân, Hà Nội', N'Thường Trú'),
                                                                                                                                                                                                 (N'Nguyễn Hải Hà', '1948-02-03', N'Bệnh viện Đại học Y ', N'Nam', N'Hải Phòng', N'Kinh', N'Không', N'Việt Nam', N'', N'', 123456790, '2001-10-11',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2019-12-15', N'Số 5 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', N'Đã mất'),
                                                                                                                                                                                                 (N'Thân Quang Khoát', '1983-02-28', N'Bệnh viện Bạch Mai', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Giảng Viên', N'Đại Học Bách Khoa Hà nội', 123456791, '2013-10-11',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2019-12-15', N'Số 35 Minh Khai, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                 (N'Kiều thị Lan', '1999-07-13', N'Bệnh viện Bạch Mai', N'Nữ', N'Hải Dương', N'Kinh', N'Không', N'Việt Nam', N'Nội Trợ', N'Tại nhà', ' 123456791', '2008-10-11',N'Cục Cảnh sát quản lý hành chính về trật tự xã hội', '2020-11-19', N'Số 15 Hoàng Mai, Quận Hoàng Mai, Hà Nội', N'Thường trú');
INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao) VALUES
                                                                                                (1, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 1 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-08'),
                                                                                                (2, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 2 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-07'),
                                                                                                (3, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 3 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-06'),
                                                                                                (4, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 4 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-05'),
                                                                                                (5, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-05');
INSERT INTO tam_vang(idNhanKhau, noiTamTru, tuNgay, denNgay, lyDo) VALUES
                                                                       (4, N'Số 8 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-11-11', '2022-12-12', NULL),
                                                                       (9, N'Số 7 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-11-10', '2022-12-11', NULL);
INSERT INTO tam_tru(idNhanKhau, noiThuongTru, noiTamTru, tuNgay, denNgay, lyDo) VALUES
    (11, N'Số 10 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Số 6 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-12-16', '2022-10-10', NULL);
INSERT INTO khai_tu(idNguoiMat, idNguoiKhai, ngayKhai, ngayMat, liDoMat) VALUES
    (12, 5, '2022-2-10', '2022-2-1', 'Đột Quỵ');
INSERT INTO chuyen_nhan_khau(idNhanKhau, ngayChuyenDi, noiChuyenDen, ghiChu) VALUES
    (13, '2022-10-10', N'Số 10 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', NULL);
INSERT INTO ho_khau_nhan_khau(idHoKhau, idNhanKhau, quanHeChuHo) VALUES
                                                                     (1, 6, N'Anh trai'),
                                                                     (1, 7, N'Vợ'),
                                                                     (2, 8, N'Bố'),
                                                                     (3, 9, N'Bố'),
                                                                     (4, 10, N'Con trai'),
                                                                     (5, 12, N'Ông ngoại'),
                                                                     (5, 13, N'Bà ngoại');
INSERT INTO khai_bao(idNhanKhau, ngayKhaiBao, trieuChung, tinhTrangSucKhoe) VALUES
    (1, '2022-10-10', N'Có', N'Ho,Sốt'),
    (2, '2022-12-12', N'Không', N'Bình thường'),
    (3, '2022-11-11', N'Có', N'Ho');
INSERT INTO test_covid(idNhanKhau, thoiDiemTest, hinhThucTest, ketQua) VALUES
    (1, '2022-10-10', N'PCR', N'Âm tính'),
    (2, '2022-12-12', N'Test nhanh', N'Dương tính'),
    (3, '2022-11-11', N'Test nhanh', N'Âm tính');
INSERT INTO cach_ly(idNhanKhau, ngayBatDau,soNgayCachLy, noiCachLy, mucDoCachLy,lyDoCachLy) VALUES
    (1, '2022-01-10',21, N'Tại nhà', N'F1',N'Tiếp xúc với F0'),
    (2, '2022-12-20',21, N'Khu A', N'Chưa rõ',N'Tiếp xúc với F0'),
    (3, '2022-05-15',21, N'Khu B', N'Chưa rõ',N'Đi từ vùng dịch về'),
    (4, '2022-12-20',21, N'Bệnh viện A', N'F2',N'Tiếp xúc với F1'),
    (5, '2022-06-22',21, N'Khu B', N'F2',N'Tiếp xúc với F1'),
    (6, '2022-11-17',21, N'Khu A', N'F0',N'Tiếp xúc với F1'),
    (7, '2022-08-20',21, N'Tại nhà', N'F1',N'Tiếp xúc với F0');

INSERT INTO tiem_covid(idNhanKhau,noiTiem,soMui) value
							(1,N'Hà Nội',1),
                            (2,N'Đại học Bách Khoa Hà Nội',2),
                            (3,N'Hưng Yên',2),
                            (4,N'Bệnh viện Bạch Mai',3),
                            (5,N'Đại học Y Hà Nội',3);