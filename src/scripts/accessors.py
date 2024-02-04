def main(text: str):
    clazz = extract_class(text)
    fields = parse_fields(text)
    print("  // region getters")
    for t, f in fields:
        print_getter(t, f)
    print("\n  // endregion")
    print("\n  // region setters")
    for t, f in fields:
        print_setter(clazz, t, f)
    for t, f in fields:
        if not isprim(t):
            print_builder(t, f)
    print("\n  // endregion")
    print_copy(clazz, fields)


def parse_fields(text) -> list[tuple[str, str]]:
    fields = []
    for line in text.splitlines():
        inp = line.strip()
        if not inp.startswith("private "):
            continue
        inp = inp[8:]
        field_type = ""
        field_name = ""
        intype = True
        inbracks = False
        for c in inp:
            if c == " ":
                if inbracks and intype:
                    field_type += c
                    continue
                if intype:
                    intype = False
                continue

            if c == ";":
                break

            if c == "<":
                inbracks = True
            if c == ">":
                inbracks = False
            if intype:
                field_type += c
            else:
                field_name += c
        fields.append((field_type, field_name))
    return fields


def print_getter(field_type: str, field_name: str):
    r = field_name
    if field_type.startswith("List<"):
        r = f"{field_name} != null ? {field_name} : List.of()"
    if field_type.startswith("Map<"):
        r = f"{field_name} != null ? {field_name} : Map.of()"
    print(
        f"""
  public {field_type} get{up(field_name)}() {{
    return {r};
  }}"""
    )


def print_setter(clazz: str, field_type: str, field_name: str):
    print(
        f"""
  public {clazz} with{up(field_name)}({field_type} {field_name}) {{
    this.{field_name} = {field_name};
    return this;
  }}"""
    )


def print_builder(field_type: str, field_name: str):
    t = field_type
    if t.startswith("Map<"):
        t = "HashMap<>"
    if t.startswith("List<"):
        t = "ArrayList<>"
    print(
        f"""
  public {field_type} with{up(field_name)}() {{
    if ({field_name} == null) {{
      {field_name} = new {t}();
    }}
    return {field_name};
  }}"""
    )


def print_copy(clazz: str, fields: list[tuple[str, str]]):
    print(
        f"""
  @Override
  public {clazz} copy() {{
    var copy = new {clazz}();"""
    )
    for t, f in fields:
        if isprim(t):
            print(f"    copy.with{up(f)}({f});")
        else:
            print(f"    Val.copy({f}, copy::with{up(f)});")
    print(
        f"""    return copy;
  }}"""
    )


def up(t):
    return t[0].upper() + t[1:]


def isprim(field_type):
    return field_type in [
        "boolean",
        "Boolean",
        "String",
        "int",
        "Integer",
        "double",
        "Double",
        "XMLGregorianCalendar",
        "Compliance",
        "ExchangeDirection",
        "UncertaintyDistribution",
        "DataDerivation",
        "RecommendationLevel",
        "ReviewMethod",
        "ReviewScope",
        "FlowType",
    ]


def extract_class(text: str) -> str:
    for line in text.splitlines():
        parts = line.strip().split(" ")
        is_class = False
        for word in parts:
            if word == "class":
                is_class = True
                continue
            if is_class:
                return word
    return "UNKNOWN"


main(
"""



@XmlAccessorType(XmlAccessType.FIELD)
public class EpdInfoExtension {

	@XmlElement(name = "safetyMargins", namespace = Vocab.EPD_2013)
	private EpdSafetyMargins safetyMargins;

	@XmlElement(name = "scenarios", namespace = Vocab.EPD_2013)
	private List<EpdScenario> scenarios;

	@XmlElement(name = "modules", namespace = Vocab.EPD_2013)
	private List<EpdModule> modules;

	@XmlElement(name = "contentDeclaration", namespace = Vocab.EPD_2019)
	private EpdContentDeclaration contentDeclaration;

	@XmlAnyElement(lax = true)
	private List<Object> any;

""",
)
