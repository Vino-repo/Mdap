package org.sample;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class fhirQuery {

	@Test

	private void fhirquery() {

		RestAssured.baseURI = "http://172.19.40.82:5867";

		String token = "eyJhbGciOiJSUzM4NCIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkYnpkNXpvZG84YTlqTFhJaDE5ci0yMUdlbm80ZGNEYU81Yk1jNHBQMm44IiwiaXNzIjoic2hpbm55LWNlbnRyYWwtY2xpZW50Iiwic3ViIjoiY2VudHJhbF9maGlyX3NlcnZpY2UiLCJhdWQiOiJodHRwczovL1JISU8xLm9yZy9GSElSQVBJIiwiU3ViamVjdElEIjoiUmVnaW9uYWwgSGVhbHRoIEluZm9ybWF0aW9uIE9yZ2FuaXphdGlvbiIsIlN1YmplY3RPcmdhbml6YXRpb24iOiJST0NIMSIsIlN1YmplY3RPcmdhbml6YXRpb25JRCI6IjIuMTYuODQwLjEuOTk5OS45Ljk5OSIsIkhvbWVDb21tdW5pdHlJRCI6IjIuMTYuODQwLjEuOTk5OS45Ljk5OSIsIlN1YmplY3RSb2xlIjoiY29kZT02NTk1MDAwNDtjb2RlU3lzdGVtPTIuMTYuODQwLjEuMTEzODgzLjMuMTguNi4xLjE1IiwiUHVycG9zZU9mVXNlIjoiY29kZT1UUkVBVE1FTlQ7Y29kZVN5c3RlbT0yLjE2Ljg0MC4xLjExMzg4My42Ljk2IiwicmVzb3VyY2VJRCI6Ijk5OTl8Mi4xNi44NDAuMS45OTk5LjkuOTk5IiwiaWF0IjoxNTc5NTk4Mjc2fQ.Lvrs3kyK4";

		Response resq = RestAssured.given().header("Authorization", "Bearer" + token).when()
				.get("/baseR4/fhir/Patient?identifier=CP56003456665");

		String body = resq.prettyPrint();
		System.out.println(body);

		int code = resq.getStatusCode();
		System.out.println("Status code--" + code);
		Assert.assertEquals(code, 200);
	}

	@Test
	private void httpReq() {

		RestAssured.baseURI = "http://172.19.40.82:5867";
		String token = "eyJhbGciOiJSUzM4NCIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkYnpkNXpvZG84YTlqTFhJaDE5ci0yMUdlbm80ZGNEYU81Yk1jNHBQMm44IiwiaXNzIjoic2hpbm55LWNlbnRyYWwtY2xpZW50Iiwic3ViIjoiY2VudHJhbF9maGlyX3NlcnZpY2UiLCJhdWQiOiJodHRwczovL1JISU8xLm9yZy9GSElSQVBJIiwiU3ViamVjdElEIjoiUmVnaW9uYWwgSGVhbHRoIEluZm9ybWF0aW9uIE9yZ2FuaXphdGlvbiIsIlN1YmplY3RPcmdhbml6YXRpb24iOiJST0NIMSIsIlN1YmplY3RPcmdhbml6YXRpb25JRCI6IjIuMTYuODQwLjEuOTk5OS45Ljk5OSIsIkhvbWVDb21tdW5pdHlJRCI6IjIuMTYuODQwLjEuOTk5OS45Ljk5OSIsIlN1YmplY3RSb2xlIjoiY29kZT02NTk1MDAwNDtjb2RlU3lzdGVtPTIuMTYuODQwLjEuMTEzODgzLjMuMTguNi4xLjE1IiwiUHVycG9zZU9mVXNlIjoiY29kZT1UUkVBVE1FTlQ7Y29kZVN5c3RlbT0yLjE2Ljg0MC4xLjExMzg4My42Ljk2IiwicmVzb3VyY2VJRCI6Ijk5OTl8Mi4xNi44NDAuMS45OTk5LjkuOTk5IiwiaWF0IjoxNTc5NTk4Mjc2fQ.Lvrs3kyK4";

		RequestSpecification httpRequest = RestAssured.given();
		// Set HTTP Headers
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Authorization", "Bearer" + token);

		Response response = httpRequest.get("/baseR4/fhir/AllergyIntolerance?patient=62099&_list=$current-allergies");

		// Get Response Body
		ResponseBody body = response.getBody();

		// Get Response Body as String
		String bodyStringValue = body.asString();
		System.out.println("Response" + bodyStringValue);

		// Get JSON Representation from Response Body
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Get specific element from JSON document
		String type = jsonPathEvaluator.get("resourceType");
		// String type = jsonPathEvaluator.get("UscdiValidation");
		// String prof = jsonPathEvaluator.get("profile");

		System.out.println("UscdiValidation--" + type);

		// Validate if the specific JSON element is equal to expected value
		Assert.assertEquals(type, "AllergyIntolerance");
		// Assert.assertEquals(type,null);

	}
}
