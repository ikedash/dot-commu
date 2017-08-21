INSERT INTO tag(tag_name) VALUES ('Java');
INSERT INTO tag(tag_name) VALUES ('JavaScript');
INSERT INTO tag(tag_name) VALUES ('JSP');
INSERT INTO tag(tag_name) VALUES ('DB');
INSERT INTO tag(tag_name) VALUES ('SQL');
INSERT INTO tag(tag_name) VALUES ('その他');
INSERT INTO users (user_id, username, encoded_password) VALUES (users_seq.nextval, 'user1', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9' /*demo*/);
INSERT INTO profile(user_id, birthday, birthplace, blood_type, country, favorite_food, first_name, hated_food, introduction, last_name, image) VALUES (users_seq.currval, '1987/07/30', '山形', 'A型', 'ニューカレドニア', '鉄板焼き', '太郎', 'セロリ', '5年目技術サポートしてます。', 'TIS', 'boy2');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.660','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。２','理由２',users_seq.currval,'2017/03/17 15:57:47.665','Good','JavaScript','プログラミングに慣れてきた、気がする２');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。３','理由３',users_seq.currval,'2017/03/17 15:58:08.536','VeryGood','Java','プログラミングに慣れてきた、気がする３');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.010','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.020','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.030','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.040','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.050','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.060','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.070','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.080','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.090','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.100','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.110','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.120','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.130','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.140','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.150','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.160','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.170','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.180','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.190','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.200','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.210','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.220','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.230','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.240','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.250','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.260','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.270','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.280','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.290','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.300','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.310','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.320','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.330','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.340','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.350','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.360','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.370','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.380','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.390','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.400','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.410','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.420','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.430','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.440','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.450','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.460','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.470','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.480','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.490','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.500','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.510','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.520','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.530','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.540','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.550','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.560','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.570','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.580','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.590','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.600','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.610','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.620','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.630','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.640','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.650','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.660','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.670','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.680','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.690','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.700','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.710','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.720','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.730','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.740','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.750','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.760','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.770','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.780','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.790','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.800','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.810','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.820','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.830','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.840','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.850','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.860','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.870','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.880','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.890','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.900','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.910','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.920','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.930','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.940','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.950','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.960','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.970','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.980','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:33.990','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'本日はあれこれ学びまして、大変勉強になりました。研修の期間がとても有意義に仕えていると実感することができます。１','理由１',users_seq.currval,'2017/03/17 15:57:34.000','VeryGood','Java','プログラミングに慣れてきた、気がする１');
INSERT INTO users (user_id, username, encoded_password) VALUES (users_seq.nextval, 'user2', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9' /*demo*/);
INSERT INTO profile(user_id, birthday, birthplace, blood_type, country, favorite_food, first_name, hated_food, introduction, last_name, image) VALUES (users_seq.currval, '1992/08/20', '群馬', 'A型', '？', 'キャラメルフラペチーノ', '麻衣', '？', '特技はソフトボール・料理・モノマネ（千と千尋のカエル）', '白石', 'girl5');
INSERT INTO report(report_id,report_body,cause,user_id,created_at,satisfaction,tag,title) VALUES (report_seq.nextval,'こんにちは



もう2月ですね...





2017年も私らしく頑張りますので、
皆さんよろしくお願い致します！







もうすぐと言いますか、
あと3日で2nd写真集が発売になります☆



色んなところで声を頂き、嬉しい反面すごくドキドキしています。




発売前日には、SHOWROOMさんで発売記念特別番組が配信されることが決定しました(^^)


初めてのSHOWROOMさんなので今からキンチョウ...


見て頂けたら嬉しいです♪','理由１',users_seq.currval,'2017/02/04 15:57:34.000','VeryGood','Java','転(o^_^o)');