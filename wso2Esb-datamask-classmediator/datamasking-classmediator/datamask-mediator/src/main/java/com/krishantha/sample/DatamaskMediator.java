package com.krishantha.sample;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatamaskMediator extends AbstractMediator {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatamaskMediator.class);

	public boolean mediate(MessageContext context)

	{
		JSONObject jsonObject = maskJsonPayload(context);
		context.setProperty("MASKED_PAYLOAD", jsonObject.toString());
		return true;
	}

	public JSONObject maskJsonPayload(MessageContext messageContext) {
		// extract properties
		String maskingKeys = (String) messageContext
				.getProperty("MASKING_KEYS");
		String stringPayload = (String) messageContext
				.getProperty("TARGET_PAYLOAD");

		JSONArray keys;
		try {
			keys = new JSONObject(maskingKeys).getJSONArray("keysToBeMasked");
		} catch (JSONException e) {
			LOGGER.error("Error parsing masking key array. > " + e.toString());
			return null;
		}
		JSONObject jsonPayload;
		try {
			jsonPayload = new JSONObject(stringPayload);
		} catch (JSONException e) {
			LOGGER.error("Error parsing Json payload. recived payload is : "
					+ messageContext.getProperty("TARGET_PAYLOAD"));
			return null;
		}
		maskJsonPayload(jsonPayload, keys);
		return jsonPayload;

	}

	public void maskJsonPayload(JSONObject jsonPayload, JSONArray keys) {

		// String contexts=null;
		JSONArray innerArray = null;
		JSONArray replacementArray = null;
		String lastContext = null;
		String contexts[] = null;
		JSONObject tmpJsonObject = jsonPayload;
		for (int k = 0; k < keys.length(); k++) {

			try {
				contexts = keys.getString(k).split("\\.");
			} catch (JSONException e) {
				LOGGER.error("Error parsing Keys. May be in invalid format");
			}
			lastContext = contexts[contexts.length - 1];
			for (String context : contexts) {

				if (!jsonPayload.has(context)) {
					break;
				} else if (jsonPayload.optJSONObject(context) != null) {
					if (context.equals(lastContext)) {
						// this mean entire onject to be masked.
						JSONObject tmpJson = new JSONObject();
						try {
							tmpJson.put("********", "************");
							jsonPayload.put(context, tmpJson);
						} catch (JSONException e) {
							LOGGER.error("Error replace data with mask value :"
									+ e.getMessage());
							return;
						}
						break;
					} else {

						try {
							jsonPayload = jsonPayload.getJSONObject(context);
						} catch (JSONException e) {
							LOGGER.error("Error parsing Json payload.");
							return;
						}
						continue;
					}
				} else if (jsonPayload.optJSONArray(context) != null) {
					try {
						innerArray = jsonPayload.getJSONArray(context);

						replacementArray = new JSONArray();
						for (int i = 0; i < innerArray.length(); i++) {
							replacementArray.put("************");
						}

						jsonPayload.put(context, replacementArray);
					} catch (JSONException e) {
						LOGGER.error("Error replace data with mask value :"
								+ e.getMessage());
						return;
					}
					break;
				} else {
					try {
						jsonPayload.put(context, "************");
					} catch (JSONException e) {
						LOGGER.error("Error replace data with mask value :"
								+ e.getMessage());
						return;
					}
					break;
				}

			}
			// toupdate the this cycle results to next iteration
			jsonPayload = tmpJsonObject;
		}
		return;

	}

}
