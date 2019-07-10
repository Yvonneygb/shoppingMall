package com.shopping.shoppingMall.service;

import com.shopping.shoppingMall.common.entity.PageVO;

public interface ClassificationService {
    PageVO findListHasAccountPage(int flag, PageVO pageVO);
}
