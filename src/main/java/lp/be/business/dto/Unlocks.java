package lp.be.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unlocks {

    private Integer id;

    private Short kit;

    private String state;

}