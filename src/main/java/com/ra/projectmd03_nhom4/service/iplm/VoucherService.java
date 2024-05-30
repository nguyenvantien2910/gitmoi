package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.iplm.VoucherDao;
import com.ra.projectmd03_nhom4.model.Voucher;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class VoucherService {
    @Autowired
    private VoucherDao voucherDao;
    @Autowired
    private HttpSession session;

    public Voucher getVoucherByCode(String code) {
        Voucher voucher = voucherDao.findByCode(code);
        if (voucher != null && voucher.getExpiryDate().isAfter(LocalDateTime.now())){
            return voucher;
        }
        return null;
    }

}
