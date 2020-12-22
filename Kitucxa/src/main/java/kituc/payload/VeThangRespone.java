package kituc.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// vì ve tháng chứa object xe+ dich vụ
// nên phải tạo VeThangRequest chỉ chứa id của 2 object thôi
// sau đó tìm kiếm và add vào
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeThangRespone {
    private int id;
    private Date thoigiansd;
    private int xeId;
    private String bienSo;
}
