package com.example.yb.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Create by :yb on 2018/4/24
 * Description:
 */
public enum  EcIcons implements Icon {

    icon_scan('\ue64c'),
    icon_ali_pay('\ue610')
    ;

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return 0;
    }
}
