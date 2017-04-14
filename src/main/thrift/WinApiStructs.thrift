namespace java model


struct TWinApiParams {
  1:i64 id,
  2: string name,
  3: string type;
}

struct TWinApiFunction {
  1:i64 id,
  2: string name,
  3: string description,
  4: list<TWinApiParams> params;
}

struct TWinApiClass {
  1:i64 id,
  2: string name,
  3: string description,
  4: list<TWinApiFunction> functions;
}

