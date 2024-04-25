/**
 * 
 */
package com.rjps.send.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.rjps.send.SpringIntegrationSenderApplication.PubsubOutboundGateway;

/**
 * 
 */
@RestController
@RequestMapping(path = "/rjps")
public class MessageController {

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	@PostMapping("/postMessage")
	public RedirectView postMessage(@RequestParam("message") String message) {
		this.messagingGateway.sendToPubsub(message);
		return new RedirectView("/");
	}
}
