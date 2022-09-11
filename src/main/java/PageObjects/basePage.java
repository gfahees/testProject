package PageObjects;

import Services.Global;
import Services.Utils;

public class basePage {

    protected static Global _global = new Global();
    protected static Utils utils = null;

    public basePage(Global global){
        _global = global;
        utils = new Utils(_global);
    }
}
