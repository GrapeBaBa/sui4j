package io.sui.models;

/**
 * Created by IntelliJ IDEA.
 * Author: kaichen
 * Date: 2022/11/2
 * Time: 20:36
 */
public enum TransactionKind {
	TransferObject,
	Publish,
	Call,
	TransferSui,
	Pay,
	PaySui,
	PayAllSui,
	ChangeEpoch
}
