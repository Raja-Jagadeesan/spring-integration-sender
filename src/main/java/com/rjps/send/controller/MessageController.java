/**
 * 
 */
package com.rjps.send.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public static final Logger logger = LogManager.getLogger(MessageController.class);

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	@PostMapping("/postMessage")
	public RedirectView postMessage(@RequestParam("message") String message) {
		this.messagingGateway.sendToPubsub(message);
		return new RedirectView("/");
	}

	@PostMapping("/postMessageLoop")
	public RedirectView postMessage(@RequestParam("message") String message, @RequestParam("count") int count,
			@RequestParam("interval") int interval) {
		for (int i = 0; i < count; i++) {
			this.messagingGateway.sendToPubsub(i + ". " + message);
			logger.info("Message sent! Payload: {}", i + ". " + message);
			try {
				Thread.sleep(interval * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new RedirectView("/");
	}
}
