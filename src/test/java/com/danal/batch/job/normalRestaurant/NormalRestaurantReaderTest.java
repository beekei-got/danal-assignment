package com.danal.batch.job.normalRestaurant;

import com.danal.batch.config.BatchTestConfig;
import com.danal.batch.config.H2Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { H2Config.class, BatchTestConfig.class })
class NormalRestaurantReaderTest {

	@Autowired
	private NormalRestaurantReader reader;

	private final String[][] mockDataList = new String[][] {
		{"1","일반음식점","07_24_04_P","3140000","3140000-101-2024-00382","2024-11-18","","03","폐업","02","폐업","2024-11-28","","","","","","158-724","서울특별시 양천구 목동 916 현대하이페리온","서울특별시 양천구 목동동로 257, 지하2층 (목동, 현대하이페리온)","07998","페스토페스토","2024-11-29 04:15:08","U","2024-12-01 02:40:00","기타","188884.075622342","447186.888604306","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"2","일반음식점","07_24_04_P","3230000","3230000-101-2024-00918","2024-11-18","","03","폐업","02","폐업","2024-11-21","","","","","","138-721","서울특별시 송파구 잠실동 40-1 롯데월드","서울특별시 송파구 올림픽로 240, 롯데백화점 지하1층 (잠실동)","05554","주식회사 달링베이커리","2024-11-22 04:15:09","U","2024-11-24 02:40:00","기타","208589.363343145","445455.90405262","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"3","일반음식점","07_24_04_P","3930000","5560000-101-2024-00291","2024-11-18","","03","폐업","02","폐업","2024-11-22","","","","031 4149422","","425-140","경기도 안산시 단원구 선부동 1189 이편한세상 선부역 어반스퀘어 상가동 105호 일부","경기도 안산시 단원구 선부광장1로 179, 이편한세상 선부역 어반스퀘어 상가동 105호 일부 (선부동)","15366","최배달K푸드","2024-11-22 16:31:38","U","2024-11-24 02:40:00","분식","182688.916840009","425741.81411192","분식","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"4","일반음식점","07_24_04_P","3330000","3330000-101-2024-00484","2024-11-18","","03","폐업","02","폐업","2024-11-24","","","","","","612-704","부산광역시 해운대구 우동 1500 벡스코","부산광역시 해운대구 APEC로 55, 벡스코 제1전시장 3A홀 (우동)","48060","꼬알라파이(KKOALAPIE) 청주충북도청점","2024-11-25 04:15:10","U","2024-11-27 02:40:00","기타","394482.139208377","187606.035424193","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"5","일반음식점","07_24_04_P","5020000","5040000-101-2024-00332","2024-11-18","","03","폐업","02","폐업","2024-11-23","","","","","25.00","791-803","경상북도 포항시 북구 두호동 1015-6 영일대 장미공원 일원","","","비진259공샐","2024-11-24 04:15:09","U","2024-11-26 02:40:00","기타","414549.907360515","287280.471955445","기타","0","0","","","","0","0","0","0","0","","0","0","N","25","","",""},
		{"6","일반음식점","07_24_04_P","3160000","3160000-101-2024-00413","2024-11-18","","03","폐업","02","폐업","2024-11-28","","","","","0.00","152-706","서울특별시 구로구 신도림동 692 디큐브시티","서울특별시 구로구 경인로 662, 지하 2층 (신도림동, 디큐브시티)","08209","금덕푸드","2024-11-29 04:15:08","U","2024-12-01 02:40:00","기타","190107.045415333","445157.626366229","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"7","일반음식점","07_24_04_P","3220000","3220000-101-2024-01407","2024-11-18","","03","폐업","02","폐업","2024-11-23","","","","031 123 4567","","135-731","서울특별시 강남구 삼성동 159 코엑스","서울특별시 강남구 영동대로 513, 코엑스 B전시관 3607 (푸드위크 박람회) (삼성동)","06164","크로롤(한시적)","2024-11-24 04:15:11","U","2024-11-26 02:40:00","기타","205130.591678902","445590.096837802","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"8","일반음식점","07_24_04_P","3740000","3760000-101-2024-00349","2024-11-18","","03","폐업","02","폐업","2024-11-24","","","","","","441-859","경기도 수원시 권선구 서둔동 296-124","경기도 수원시 권선구 세화로134번길 37, 전시장 일부 (서둔동)","16621","드 포스트레","2024-11-25 04:15:09","U","2024-11-27 02:40:00","기타","199672.086338054","418258.297963828","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""},
		{"9","일반음식점","07_24_04_P","3010000","3010000-101-2024-00552","2024-11-18","","03","폐업","02","폐업","2024-11-28","","","","","3.30","100-747","서울특별시 중구 충무로1가 52-5 신세계백화점건물","서울특별시 중구 소공로 63, 신세계백화점건물 지하1층 (충무로1가)","04530","금덕푸드","2024-11-29 04:15:08","U","2024-12-01 02:40:00","기타","198263.90839194","450960.762964932","기타","0","0","","","","0","0","0","0","0","","0","0","N","3.3","","",""},
		{"10","일반음식점","07_24_04_P","4040000","4040000-101-2024-00441","2024-11-18","","03","폐업","02","폐업","2024-11-19","","","","","","465-030","경기도 하남시 신장동 570 하남두산위브파크","경기도 하남시 하남대로 747, 하남두산위브파크 홈플러스 1층 일부호 (신장동)","12961","Cafe Walnut(커피,호두나무)","2024-11-20 04:15:09","U","2024-11-22 02:40:00","기타","218722.326285059","448437.965903226","기타","0","0","","","","0","0","0","0","0","","0","0","N","0","","",""}
	};

	@Test
	@DisplayName("normalRestaurantReader 테스트")
	void normalRestaurantReader() throws Exception {
		reader.open(new ExecutionContext());
		for (String[] mockData : mockDataList) {
			String[] readData = Arrays.stream(reader.read().getColumnValues())
				.map(v -> Optional.ofNullable(v).map(String::valueOf).orElse(""))
					.toArray(String[]::new);
			assertThat(readData).isEqualTo(mockData);
		}
		reader.close();
	}

}