 package br.com.uol.service;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

@RequestScoped
public abstract class Service implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserTransaction ut;
	
	protected void beginTransation() {
		try {
			if(ut.getStatus() != Status.STATUS_ACTIVE) {
				ut.begin();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void commitTransaction() {
		try {
			if(ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.commit();
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		}
	}
	
	protected void rollbackTransaction() {
		try {
			if(ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
