package com.thud.dulichdalat.ui.gioithieu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class GioiThieuViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public GioiThieuViewModel() {
        mText = new MutableLiveData<>();
        String strGioiThieu ="Đà Lạt từ lâu đã trở thành một trong"+
        "những thành phố du lịch nổi tiếng nhất của Việt Nam, với nhiều cảnh"+
        "quan thiên nhiên đẹp và thơ mộng. Có ai đó đã từng ví Đà Lạt cũng"+
        "giống một thành phố trong rừng, nơi khí trời se lạnh, thiên nhiên và"+
        "con người gần gũi như luôn hoà quyện, đan xen vào nhau.";
        strGioiThieu += "\n\nAi đến Đà Lạt đều phải thốt lên rằng:"+
        "Ôi! Sao Đà Lạt đẹp đến thế! Đẹp mê hồn người lữ khách, đẹp cả trong"+
        "tim người chưa đến Đà Lạt bao giờ. Phải chăng người ta yêu Đà Lạt"+
        "bởi những dòng thác cuộn trào, ảo ảnh sương khói của hương hoa, của"+
        "nước Hồ Xuân Hương dịu mát.";
        strGioiThieu += "\n\nNúi Lang Biang nằm trên địa bàn huyện"+
        "Lạc Dương, cách trung tâm thành phố Đà Lạt 12km về phía bắc. Núi"+
        "Lang Biang còn được gọi là Núi Mẹ, gồm 2 ngọn, có độ cao 2.167m.";
        mText.setValue(strGioiThieu);
    }
    public LiveData<String> getText() {
        return mText;
    }
}
