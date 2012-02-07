package org.springframework.data.services;

import java.net.URI;

import org.springframework.data.services.util.BeanUtils;
import org.springframework.data.services.util.UriUtils;

/**
 * @author Jon Brisbin <jon@jbrisbin.com>
 */
public class BeanPropertyResourceHandler extends AbstractResourceHandler {

  public BeanPropertyResourceHandler(URI baseUri) {
    super(baseUri);
  }

  @Override public boolean supports(Resource resource, Object... args) {
    return BeanUtils.hasProperty(UriUtils.tail(baseUri, resource.uri()).getPath(), resource.target());
  }

  @Override public Resource handle(Resource resource, Object... args) {
    URI tail = UriUtils.tail(baseUri, resource.uri());
    Object o = BeanUtils.findFirst(tail.getPath(), resource.target());
    return new SimpleResource(tail, o);
  }

}
