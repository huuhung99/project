package kituc.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeThangRespone {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thoigiansd;
    private int xeId;
    private String bienSo;
}
