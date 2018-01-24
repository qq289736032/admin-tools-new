package com.mokylin.cabal.common.io;

import java.io.Serializable;

public interface ChannelId extends Serializable, Comparable<ChannelId> {

	String asShortText();

	String asLongText();
}
