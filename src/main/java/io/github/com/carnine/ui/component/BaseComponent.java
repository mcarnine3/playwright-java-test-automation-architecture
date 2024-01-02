package io.github.com.carnine.ui.component;

import com.microsoft.playwright.Page;

/**
 * @author tahanima
 */
public abstract class BaseComponent {

    protected Page page;

    protected BaseComponent(final Page page) {
        this.page = page;
    }
}
