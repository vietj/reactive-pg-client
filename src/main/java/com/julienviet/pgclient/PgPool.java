/*
 * Copyright (C) 2017 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.julienviet.pgclient;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;

/**
 * A pool of connection.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface PgPool extends PgOperations {

  @Override
  default PgPool preparedQuery(String sql, Handler<AsyncResult<PgResult<Tuple>>> handler) {
    return (PgPool) PgOperations.super.preparedQuery(sql, handler);
  }

  @Override
  PgPool preparedQuery(String sql, Tuple arguments, Handler<AsyncResult<PgResult<Tuple>>> handler);

  @Override
  PgPool preparedBatch(String sql, List<Tuple> batch, Handler<AsyncResult<PgBatchResult<Tuple>>> handler);

  /**
   * Obtain a connection from the pool.
   *
   * @param handler the handler that will get the connection result
   */
  void getConnection(Handler<AsyncResult<PgConnection>> handler);

  /**
   * Close the pool and release the associated resources.
   */
  void close();

}
