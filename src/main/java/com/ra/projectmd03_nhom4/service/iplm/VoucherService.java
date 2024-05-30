package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.iplm.VoucherDao;
import com.ra.projectmd03_nhom4.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class VoucherService {
    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private HttpSession session;


    public Voucher getVoucherByCode(String code) {
        List<Voucher> voucherList = voucherDao.findAllCode();
        Voucher voucher = null;
        for (Voucher voucher1 : voucherList) {
            if (voucher1.getVoucherCode().equals(code)) {
                voucher = voucher1;
            }
        }
        if (voucher != null && (voucher.getExpiryDate()).after(new Date()) ){
            return voucher;
        }
        return null;
    }


    public boolean checkVoucherCode(String voucherCode) {
        return voucherDao.checkVoucherCode(voucherCode);
    }

    public Long findAllCode(String code){
        return voucherDao.findAllCode(code);
    };

    public Voucher findByCode(Long code){
        return voucherDao.findByCode(code);
    };

    public boolean save(Voucher voucher){
        return voucherDao.save(voucher);
    };

    public boolean update(Voucher voucher) {
        return voucherDao.update(voucher);
    };

    public boolean delete(Voucher voucher){
        return voucherDao.delete(voucher);
    };

    public List<Voucher> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        return voucherDao.findAll(pageNo, pageSize, sortField, sortDirection, searchQuery);
    };

}
