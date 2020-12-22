package kituc.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewVeThangRequest {
    private Date thoigiansd;
    private String bienSo;
}
