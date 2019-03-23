package core.constants;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class spellResult {
    int code;
    int pos;
    int row;
    int col;
    int len;
    String word;
    String s;

}
