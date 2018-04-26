package com.example.yb.festec;

import com.example.yb.latte_core.activities.ProxyActivity;
import com.example.yb.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
