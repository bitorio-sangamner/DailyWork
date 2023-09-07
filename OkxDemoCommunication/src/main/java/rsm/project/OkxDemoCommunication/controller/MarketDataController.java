package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.marketData.MarketDataAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketDataController {
    @Autowired
    MarketDataAPIService marketDataAPIService;

    @GetMapping("/market_data/get_tickers")
    public ResponseEntity<JSONObject> getTickers(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String instFamily = jsonData.getString("instFamily");
        String uly = jsonData.getString("uly");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getTickers(instType, instFamily, uly);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_ticker/{instId}")
    public ResponseEntity<JSONObject> getTicker(@PathVariable("instId") String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getTicker(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_index_ticker")
    public ResponseEntity<JSONObject> getIndexTickers(@RequestBody JSONObject jsonData) {
        String quoteCcy = jsonData.getString("quoteCcy");
        String instId = jsonData.getString("instId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getIndexTickers(quoteCcy, instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_order_book")
    public ResponseEntity<JSONObject> getOrderBook(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String sz = jsonData.getString("sz");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getOrderBook(instId, sz);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_order_book_lite/{instId}")
    public ResponseEntity<JSONObject> getOrderLiteBook(@PathVariable String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getOrderLiteBook(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_candlesticks")
    public ResponseEntity<JSONObject> getCandlesticks(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getCandlesticks(instId, after, before, bar, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_candlesticks_history")
    public ResponseEntity<JSONObject> getCandlesticksHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getCandlesticksHistory(instId, after, before, bar, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_index_candlesticks")
    public ResponseEntity<JSONObject> getIndexCandlesticks(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getIndexCandlesticks(instId, after, before, bar, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_index_candlesticks_history")
    public ResponseEntity<JSONObject> getIndexCandlesticksHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getIndexCandlesticksHistory(instId, after, before, bar, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_mark_price_candlesticks")
    public ResponseEntity<JSONObject> getMarkPriceCandlesticks(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getMarkPriceCandlesticks(instId, after, before, bar, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_mark_price_candlesticks_history")
    public ResponseEntity<JSONObject> getMarkPriceCandlesticksHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getMarkPriceCandlesticksHistory(instId, after, before, bar, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_trades")
    public ResponseEntity<JSONObject> getTrades(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getTrades(instId, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_trades_history")
    public ResponseEntity<JSONObject> getTradesHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String bar = jsonData.getString("bar");
        String type = jsonData.getString("type");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getTradesHistory(instId, after, before, bar, type);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_24_platform_volume")
    public ResponseEntity<JSONObject> getTotalVolume() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getTotalVolume();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_oracle_contract")
    public ResponseEntity<JSONObject> getOracle() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getOracle();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_exchange_rate")
    public ResponseEntity<JSONObject> getExchangeRate() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getExchangeRate();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_index_components/{index}")
    public ResponseEntity<JSONObject> getIndexComponents(@PathVariable("index") String index) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getIndexComponents(index);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_block_tickers")
    public ResponseEntity<JSONObject> getBlockTickers(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getBlockTickers(instType, uly, instFamily);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_block_ticker/{instId}")
    public ResponseEntity<JSONObject> getBlockTicker(@PathVariable("instId") String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getBlockTicker(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_block_trade/{instId}")
    public ResponseEntity<JSONObject> getBlockTrades(@PathVariable("instId") String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getBlockTrades(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/market_data/get_instrument_family_trades/{instFamily}")
    public ResponseEntity<JSONObject> getInstrumentFamilyTrades(@PathVariable("instFamily") String instFamily) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = marketDataAPIService.getInstrumentFamilyTrades(instFamily);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
