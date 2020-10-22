import com.tnt.model.*;

public class afafa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TntService tntSvc = new TntService();
		TntVO tntVO_pocket = tntSvc.getOneTntPocket("TNT000001");
		int a = tntVO_pocket.getTnt_bank();
		System.out.println(a);

	}

}
