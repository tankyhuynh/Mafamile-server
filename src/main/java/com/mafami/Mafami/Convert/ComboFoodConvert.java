package com.mafami.Mafami.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.ComboFoodEntity;
import com.mafami.Mafami.Entity.MenuEntity;
import com.mafami.Mafami.model.PriceModel;

@Component
public class ComboFoodConvert {

	public MenuEntity fromCombo_To_Menu(ComboFoodEntity comboFoodEntity) {
		MenuEntity menuEntity = new MenuEntity();
		menuEntity.setName(comboFoodEntity.getName());
		menuEntity.setImage(comboFoodEntity.getThumbnail());
		
		List<PriceModel> listPrice = new ArrayList<PriceModel>();
		PriceModel priceModel = new PriceModel();
		priceModel.setPrice( comboFoodEntity.getPrice() );
		listPrice.add(priceModel);
		menuEntity.setPrice( listPrice );
		
		return menuEntity;
	}
	
}
