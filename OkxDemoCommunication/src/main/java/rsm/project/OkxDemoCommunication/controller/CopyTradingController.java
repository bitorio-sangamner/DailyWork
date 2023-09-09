package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.copytrading.param.AlgoOrder;
import com.okex.open.api.bean.copytrading.param.CloseSubposition;
import com.okex.open.api.bean.copytrading.param.SetInstruments;
import com.okex.open.api.service.copytrading.CopytradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Query;

@RestController()
public class CopyTradingController {
    @Autowired
    private CopytradingAPIService copytradingAPIService;

    @GetMapping("/copy_trading/get_instruments")
    public ResponseEntity<JSONObject> getInstruments() {
        JSONObject jsonObject = copytradingAPIService.getInstruments();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/current_subpositions/{instId}")
    public ResponseEntity<JSONObject> currentSubpositions(@PathVariable String instId) {
        JSONObject jsonObject = copytradingAPIService.currentSubpositions(instId);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/get_subposition_history")
    public ResponseEntity<JSONObject> subpositionsHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = copytradingAPIService.subpositionsHistory(instId, after, before, limit);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/copy_trading/place_leading_stop_order")
    public ResponseEntity<JSONObject> algoOrder(AlgoOrder algoOrder) {
        JSONObject jsonObject = copytradingAPIService.algoOrder(algoOrder);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/copy_trading/close_leading_subposition")
    public ResponseEntity<JSONObject> closeSubposition(CloseSubposition closeSubposition) {
        JSONObject jsonObject = copytradingAPIService.closeSubposition(closeSubposition);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/copy_trading/set_leading_instruments")
    public ResponseEntity<JSONObject> setInstruments(SetInstruments setInstruments) {
        JSONObject jsonObject = copytradingAPIService.setInstruments(setInstruments);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/get_profit_sharing_details")
    public ResponseEntity<JSONObject> getProfitSharingDetails(JSONObject jsonData) {
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = copytradingAPIService.getProfitSharingDetails(after, before, limit);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/get_total_profit_sharing")
    public ResponseEntity<JSONObject> getTotalProfitSharing() {
        JSONObject jsonObject = copytradingAPIService.getTotalProfitSharing();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/get_unrealized_profit_sharing_details")
    public ResponseEntity<JSONObject> getUnrealizedProfitSharingDetails() {
        JSONObject jsonObject = copytradingAPIService.getUnrealizedProfitSharingDetails();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
