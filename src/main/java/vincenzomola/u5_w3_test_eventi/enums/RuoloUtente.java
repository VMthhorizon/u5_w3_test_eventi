package vincenzomola.u5_w3_test_eventi.enums;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum RuoloUtente {
    @JsonAlias({"user", "USER"})
    USER,
    @JsonAlias({"organizzatore", "ORGANIZZATORE"})
    ORGANIZZATORE
}
