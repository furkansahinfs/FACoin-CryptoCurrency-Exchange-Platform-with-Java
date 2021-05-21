package view.decorator;

import service.FavListService;
import view.AppView;
import view.HomeView;

public class FavListDecorator extends JListDecorator {

	public FavListDecorator(AppView view) {
		super(view);
	}

	@Override
	public void set() {
		update();
		view.setList(this.list);
		
	}
	
	@Override
	public void update() {
		String userId = ((HomeView) this.view).getUserId();
		FavListService fls = new FavListService(userId);
		this.list = fls.getList();
	}
}
