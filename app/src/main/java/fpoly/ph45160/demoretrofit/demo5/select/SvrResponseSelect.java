package fpoly.ph45160.demoretrofit.demo5.select;

import fpoly.ph45160.demoretrofit.demo5.SanPham;

public class SvrResponseSelect {
    private SanPham[] products;
    private  String message;

    public SanPham[] getProducts() {
        return products;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
